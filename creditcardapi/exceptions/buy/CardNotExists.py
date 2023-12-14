from exceptions.generic.NotFoundException import NotFoundException

class CardNotExistsException(NotFoundException):
    def __init__(self, message="The given card does not exists in our database"):
        self.message = message
        super().__init__(self.message)