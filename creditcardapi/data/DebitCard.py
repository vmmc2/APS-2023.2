from dataclasses import dataclass
from marshmallow import Schema, fields as ma_fields

@dataclass
class DebitCard:
    numero_cartao:   str
    data_vencimento: str
    cvv:             str
    nome_titular:    str
    bandeira:        str
    cpf:             str
    saldo:           int

class DebitCardSchema(Schema):
    numero_cartao   = ma_fields.String(required=True)
    data_vencimento = ma_fields.String(required=True)
    cvv             = ma_fields.String(required=True)
    nome_titular    = ma_fields.String(required=True)
    bandeira        = ma_fields.String(required=True)
    cpf             = ma_fields.String(required=True)
    saldo           = ma_fields.Integer(required=False, missing=100)