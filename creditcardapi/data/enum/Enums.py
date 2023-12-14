from enum import Enum

class Status(Enum):
    SUCCESS = 1
    ERROR   = 2

class PaymentMethod(Enum):
    CREDIT_CARD = "CREDITO"
    DEBIT_CARD  = "DEBITO"