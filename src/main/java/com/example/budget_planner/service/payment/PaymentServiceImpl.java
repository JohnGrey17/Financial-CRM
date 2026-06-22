package com.example.budget_planner.service.payment;

import com.example.budget_planner.entity.Payment;
import com.example.budget_planner.entity.Project;
import com.example.budget_planner.enums.PaymentStatus;
import com.example.budget_planner.repository.PaymentRepository;
import com.example.budget_planner.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Payment create(Long projectId, Payment payment) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));

        payment.setProject(project);

        if (payment.getStatus() == null) {
            payment.setStatus(PaymentStatus.DRAFT);
        }

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> findByProjectId(Long projectId) {
        return paymentRepository.findByProject_Id(projectId);
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    @Override
    public Payment update(Long id, Payment payment) {
        Payment existingPayment = findById(id);

        existingPayment.setName(payment.getName());
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setPaymentDate(payment.getPaymentDate());

        return paymentRepository.save(existingPayment);
    }

    @Override
    public void delete(Long id) {
        Payment payment = findById(id);
        paymentRepository.delete(payment);
    }

    @Override
    public Payment approveByDivisionManager(Long id) {
        Payment payment = findById(id);

        if (payment.getStatus() != PaymentStatus.PENDING_DIVISION_APPROVAL) {
            throw new RuntimeException("Payment must be submitted to division approval first");
        }

        payment.setStatus(PaymentStatus.PENDING_DEPARTMENT_APPROVAL);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment approveByDepartmentManager(Long id) {
        Payment payment = findById(id);

        if (payment.getStatus() != PaymentStatus.PENDING_DEPARTMENT_APPROVAL) {
            throw new RuntimeException("Payment must be approved by division manager first");
        }

        payment.setStatus(PaymentStatus.APPROVED);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment reject(Long id) {
        Payment payment = findById(id);

        if (payment.getStatus() == PaymentStatus.APPROVED) {
            throw new RuntimeException("Approved payment cannot be rejected");
        }

        payment.setStatus(PaymentStatus.REJECTED);

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> findPendingByDepartmentId(Long departmentId) {
        return paymentRepository.findByProject_Division_Department_IdAndStatusIn(
                departmentId,
                List.of(
                        PaymentStatus.PENDING_DIVISION_APPROVAL,
                        PaymentStatus.PENDING_DEPARTMENT_APPROVAL
                )
        );
    }

    @Override
    public List<Payment> findByProjectIdAndStatus(Long projectId, PaymentStatus status) {
        return paymentRepository.findByProject_IdAndStatus(projectId, status);
    }

    @Override
    public Payment submitToDivisionApproval(Long id) {
        Payment payment = findById(id);

        if (payment.getStatus() != PaymentStatus.DRAFT) {
            throw new RuntimeException("Only DRAFT payments can be submitted to division approval");
        }

        payment.setStatus(PaymentStatus.PENDING_DIVISION_APPROVAL);

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> findPendingByDivisionId(Long divisionId) {
        return paymentRepository.findByProject_Division_IdAndStatus(
                divisionId,
                PaymentStatus.PENDING_DIVISION_APPROVAL
        );
    }

    @Override
    public List<Payment> findPendingDepartmentApprovals(Long departmentId) {

        return paymentRepository
                .findByProject_Division_Department_IdAndStatus(
                        departmentId,
                        PaymentStatus.PENDING_DEPARTMENT_APPROVAL
                );
    }
}