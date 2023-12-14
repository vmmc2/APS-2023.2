from exceptions.generic.BadRequestException import BadRequestException

class CardAlreadyExistsException(BadRequestException):
    def __init__(self, message="The given card already exists in our database"):
        self.message = message
        super().__init__(self.message)