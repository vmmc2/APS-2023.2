from dataclasses import dataclass
from marshmallow import Schema, fields as ma_fields
from marshmallow import post_dump

@dataclass
class GenericResponse:
    id:      str
    status:  str
    error:   str = None
    message: str = None

class GenericResponseSchema(Schema):
    id      = ma_fields.String(required=True)
    status  = ma_fields.String(required=True)
    error   = ma_fields.String(required=False)
    message = ma_fields.String(required=False)

    @post_dump
    def remove_none_values(self, data, **kwargs):
        return {key: value for key, value in data.items() if value is not None}