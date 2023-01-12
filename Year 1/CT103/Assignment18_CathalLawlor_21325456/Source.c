//Cathal Lawlor - 21325456 - 14/02/2022



#include <stdio.h>
#include <stdlib.h>


typedef struct {
    int day, month, year;
} date;

typedef struct {
    char name[50];
    int accountNumber;
    double balance;
    date lastTrans;
} customer;


void getCustomerName(customer* cptr);
void getAccountNumber(customer* cptr);
void getLastTransDate(customer* cptr);
void getBalance(customer* cptr);
void printCustomer(customer* cptr);



void main() {
    int i;
    char temp[100];
    customer customers[3];
    for (i = 0; i < 3; i++) {
        getCustomerName(&customers[i]);
        getAccountNumber(&customers[i]);
        getLastTransDate(&customers[i]);
        getBalance(&customers[i]);
        gets_s(temp, 100);
        printf("\n");
    }
    printf("\n\n%25s\t%13s\t%12s\t%s\n\n", "Name", "Account Number",
        "Balance", "Date of Last Transaction");
    for (i = 0; i < 3; i++) {
        printCustomer(&customers[i]);
    }
}

void getCustomerName(customer* cptr) {
    printf("Enter customer name: ");
    scanf_s("%s", &cptr->name, 50);
}

void getAccountNumber(customer* cptr) {
    printf("Enter account number: ");
    scanf_s("%d", &cptr->accountNumber);
}

void getLastTransDate(customer* cptr) {
    printf("Enter last transaction date in format dd/mm/yyyy: ");
    scanf_s("%d/%d/%d", &cptr->lastTrans.day, &cptr->lastTrans.month, &cptr->lastTrans.year);
}

void getBalance(customer* cptr) {
    printf("Enter current balance: ");
    scanf_s("%lf", &cptr->balance);
}

void printCustomer(customer* cptr) {
    printf("\n%s \t%d \t0.2%lf \t%d/%d/%d \n\n", cptr->name, cptr->accountNumber, cptr->balance, cptr->lastTrans.day, cptr->lastTrans.month, cptr->lastTrans.year);
}