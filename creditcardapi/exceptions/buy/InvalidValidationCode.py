from exceptions.generic.PaymentException import PaymentException

class InvalidValidationCodeException(PaymentException):
    def __init__(self, message="The given CVV is not valid"):
        self.message = message
        super().__init__(self.message)