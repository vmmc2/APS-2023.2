from exceptions.generic.NotFoundException import NotFoundException

class UserNotHaveCardsException(NotFoundException):
    def __init__(self, message="The given user does not have any cards registered."):
        self.message = message
        super().__init__(self.message)