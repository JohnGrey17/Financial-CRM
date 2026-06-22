# Budget Planner API Overview

## DepartmentRestController

### Create Department
POST /api/departments

### Get All Departments
GET /api/departments

### Get Department By Id
GET /api/departments/{id}

### Update Department
PUT /api/departments/{id}

### Delete Department
DELETE /api/departments/{id}

---

## DivisionRestController

### Create Division
POST /api/departments/{departmentId}/divisions

### Get All Divisions By Department
GET /api/departments/{departmentId}/divisions

### Get Division By Id
GET /api/divisions/{id}

### Update Division
PUT /api/divisions/{id}

### Delete Division
DELETE /api/divisions/{id}

---

## ProjectRestController

### Create Project
POST /api/divisions/{divisionId}/projects

### Get All Projects By Division
GET /api/divisions/{divisionId}/projects

### Get Project By Id
GET /api/projects/{id}

### Update Project
PUT /api/projects/{id}

### Delete Project
DELETE /api/projects/{id}

---

## PaymentRestController

### Create Payment
POST /api/projects/{projectId}/payments

### Get All Payments By Project
GET /api/projects/{projectId}/payments

### Get Payment By Id
GET /api/payments/{id}

### Update Payment
PUT /api/payments/{id}

### Delete Payment
DELETE /api/payments/{id}

### Approve By Division Manager
PATCH /api/payments/{id}/approve/division

### Approve By Department Manager
PATCH /api/payments/{id}/approve/department

### Reject Payment
PATCH /api/payments/{id}/reject

---

# Planned Next Controllers

## StatisticsController

GET /api/departments/{id}/budget

GET /api/divisions/{id}/budget

GET /api/projects/{id}/budget

## DashboardController

GET /api/dashboard

## Payment Workflow Controller

GET /api/approvals/pending

GET /api/approvals/division

GET /api/approvals/department

## Search Controller

GET /api/payments/search

GET /api/projects/search
