import re
from datetime import datetime

from data.CreditCard import CreditCard
from data.DebitCard import DebitCard

class CardValidator:
    def __init__(self, card):
        self.card_number = card['numero_cartao']
        self.cvv = card['cvv']
        self.expiration_date = card['data_vencimento']

    def validate_card_number(self):
        # Example regex for matching common credit card patterns
        pattern = re.compile(r'^\d{4}-?\d{4}-?\d{4}-?\d{4}$')
        return bool(re.match(pattern, self.card_number))

    def validate_cvv(self):
        # CVV should be a 3 or 4 digit number
        pattern = re.compile(r'^\d{3,4}$')
        return bool(re.match(pattern, self.cvv))

    def validate_expiration_date(self):
        # Expiration date should be in the format MM/YYYY
        try:
            expiration_date = datetime.strptime(self.expiration_date, "%m/%Y")
            current_date = datetime.now()
            return expiration_date > current_date
        except ValueError:
            return False

    def validate(self):
        return (
            self.validate_card_number()
            and self.validate_cvv()
            and self.validate_expiration_date()
        )
    
class NewCardValidator:
    def __init__(self, card):
        self.card_number = card['numero']
        self.cvv = card['cvv']
        self.expiration_date = card['dataValidade']

    def validate_card_number(self):
        # Example regex for matching common credit card patterns
        pattern = re.compile(r'^\d{16}$')
        return bool(re.match(pattern, self.card_number))

    def validate_cvv(self):
        # CVV should be a 3 or 4 digit number
        pattern = re.compile(r'^\d{3,4}$')
        return bool(re.match(pattern, self.cvv))

    def validate_expiration_date(self):
        # Expiration date should be in the format MM/YYYY
        try:
            expiration_date = datetime.strptime(self.expiration_date, "%Y-%m")
            current_date = datetime.now()
            return expiration_date > current_date
        except ValueError:
            return False

    def validate(self):
        print(self.validate_card_number())
        print(self.validate_cvv())
        print(self.validate_expiration_date())
        return (
            self.validate_card_number()
            and self.validate_cvv()
            and self.validate_expiration_date()
        )