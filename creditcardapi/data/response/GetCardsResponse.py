from dataclasses import dataclass
from marshmallow import Schema, fields as ma_fields
from marshmallow import post_dump

@dataclass
class GetCardsResponse:
    id:           str
    status:       str
    credit_cards: dict
    debit_cards:  dict
    error:        str = None

class GetCardsResponseSchema(Schema):
    id           = ma_fields.String(required=True)
    status       = ma_fields.String(required=True)
    credit_cards = ma_fields.Dict(required=True)
    debit_cards  = ma_fields.Dict(required=True)
    error        = ma_fields.String(required=False)

    @post_dump
    def remove_none_values(self, data, **kwargs):
        return {key: value for key, value in data.items() if value is not None}