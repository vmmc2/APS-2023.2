card_fields_to_filter = ["bandeira", "data_vencimento", "nome_titular", "numero_cartao"]

class CardFilter:
    def __init__(self, card_data):
        self.card_data = card_data

    def filter_fields(self, card_data):
        filtered_json = {}
        for field in card_fields_to_filter:
            if field in card_data:
                filtered_json[field] = card_data[field]
        return filtered_json

    def hide_middle_digits(self, card_number):
        return card_number[:4] + "*" * 8 + card_number[-4:]
    
    def filter(self):
        filtered_card_data = self.filter_fields(self.card_data)
        filtered_card_data["numero_cartao"] = self.hide_middle_digits(filtered_card_data["numero_cartao"])

        return filtered_card_data