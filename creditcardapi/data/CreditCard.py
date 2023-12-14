from dataclasses import dataclass
from marshmallow import Schema, fields as ma_fields

@dataclass
class CreditCard:
    numero_cartao:   str
    data_vencimento: str
    cvv:             str
    nome_titular:    str
    bandeira:        str
    cpf:             str
    credito:         int

class CreditCardSchema(Schema):
    numero_cartao   = ma_fields.String(required=True)
    data_vencimento = ma_fields.String(required=True)
    cvv             = ma_fields.String(required=True)
    nome_titular    = ma_fields.String(required=True)
    bandeira        = ma_fields.String(required=True)
    cpf             = ma_fields.String(required=True)
    credito         = ma_fields.Integer(required=False, missing=1000)