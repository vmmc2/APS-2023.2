from dataclasses import dataclass
from marshmallow import Schema, fields as ma_fields
from marshmallow_enum import EnumField

from data.enum.Enums import PaymentMethod

@dataclass
class BuyRequest:
    numeroCartao:   str
    cpf:             str
    cvv:             int
    valorCompra:    int
    tipoCompra:     PaymentMethod

class BuyRequestSchema(Schema):
    numeroCartao   = ma_fields.String(required=True)
    cpf            = ma_fields.String(required=True)
    cvv            = ma_fields.Integer(required=True)
    valorCompra    = ma_fields.Integer(required=True)
    tipoCompra     = EnumField(enum=PaymentMethod, by_value=True, required=True)