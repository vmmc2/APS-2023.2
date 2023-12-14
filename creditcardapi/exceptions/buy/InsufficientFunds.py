from exceptions.generic.PaymentException import PaymentException

class InsufficientFundsException(PaymentException):
    def __init__(self, message="The given card does not have sufficient funds to complete the transaction"):
        self.message = message
        super().__init__(self.message)