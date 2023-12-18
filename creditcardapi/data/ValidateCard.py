from dataclasses import dataclass
from marshmallow import Schema, fields as ma_fields

@dataclass
class ValidateRequest:
    numero:          str
    cvv:             str
    titular:         str
    dataValidade:    str
    bandeira:        str
    tipoCartao:      str
    cpf:             str

class ValidateRequestSchema(Schema):
    numero          = ma_fields.String(required=True)
    cvv             = ma_fields.String(required=True)
    titular         = ma_fields.String(required=True)
    dataValidade    = ma_fields.String(required=True)
    bandeira        = ma_fields.String(required=True)
    tipoCartao      = ma_fields.String(required=True)
    cpf             = ma_fields.String(required=True)