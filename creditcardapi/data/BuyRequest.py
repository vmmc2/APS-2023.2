from dataclasses import dataclass
from marshmallow import Schema, fields as ma_fields
from marshmallow_enum import EnumField

from data.enum.Enums import PaymentMethod

@dataclass
class BuyRequest:
    numero_cartao:   str
    cpf:             str
    cvv:             int
    valor_compra:    int
    tipo_compra:     PaymentMethod

class BuyRequestSchema(Schema):
    numero_cartao   = ma_fields.String(required=True)
    cpf             = ma_fields.String(required=True)
    cvv             = ma_fields.Integer(required=True)
    valor_compra    = ma_fields.Integer(required=True)
    tipo_compra     = EnumField(enum=PaymentMethod, by_value=True, required=True)