import uuid
from flask import Flask, request, jsonify
from http import HTTPStatus

from data.BuyRequest import *
from data.CreditCard import *
from data.DebitCard import *
from data.ValidateCard import *
from data.response.GenericResponse import *
from data.response.GetCardsResponse import *
from data.enum.Enums import *

from marshmallow import ValidationError

from exceptions.buy.InsufficientFunds import InsufficientFundsException
from exceptions.buy.InvalidValidationCode import InvalidValidationCodeException
from exceptions.buy.CardNotExists import CardNotExistsException
from exceptions.buy.UserNotHaveCards import UserNotHaveCardsException
from exceptions.register.CardAlreadyExists import CardAlreadyExistsException
from exceptions.generic.BadRequestException import BadRequestException
from exceptions.generic.PaymentException import PaymentException
from exceptions.generic.NotFoundException import NotFoundException

from filter.CardFilter import CardFilter

from validator.CardValidator import CardValidator, NewCardValidator

app = Flask(__name__)
registered_cards = {PaymentMethod.CREDIT_CARD: dict(), PaymentMethod.DEBIT_CARD: dict()}

@app.route('/')
def hello_world():
    return 'Hello, World!'

@app.route('/register/credit', methods=['POST'])
def register_credit_card():
    # Generate a random ID for the response
    id = str(uuid.uuid4())

    try: 
        request_data = request.get_json()
        creditcard = CreditCardSchema().load(request_data)

        if not CardValidator(creditcard).validate():
            raise BadRequestException(message="The given card is invalid")

        if not creditcard['cpf'] in registered_cards[PaymentMethod.CREDIT_CARD]:
            registered_cards[PaymentMethod.CREDIT_CARD][creditcard['cpf']] = dict()

        if creditcard['numero_cartao'] in registered_cards[PaymentMethod.CREDIT_CARD][creditcard['cpf']]:
            raise CardAlreadyExistsException()
        else:
            registered_cards[PaymentMethod.CREDIT_CARD][creditcard['cpf']][creditcard['numero_cartao']] = creditcard

        response = GenericResponse(id, status=Status.SUCCESS.name, message="The card was successfully registered")
        return jsonify(GenericResponseSchema().dump(response)), HTTPStatus.OK, {"Content-Type": "application/json"}
    
    except ValidationError as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.BAD_REQUEST

    except BadRequestException as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.BAD_REQUEST
    
    except Exception as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.INTERNAL_SERVER_ERROR

@app.route('/register/debit', methods=['POST'])
def register_debit_card():
    # Generate a random ID for the response
    id = str(uuid.uuid4())

    try: 
        request_data = request.get_json()
        creditcard = DebitCardSchema().load(request_data)

        if not CardValidator(creditcard).validate():
            raise BadRequestException(message="The given card is invalid")

        if not creditcard['cpf'] in registered_cards[PaymentMethod.DEBIT_CARD]:
            registered_cards[PaymentMethod.DEBIT_CARD][creditcard['cpf']] = dict()

        if creditcard['numero_cartao'] in registered_cards[PaymentMethod.DEBIT_CARD][creditcard['cpf']]:
            raise CardAlreadyExistsException()
        else:
            registered_cards[PaymentMethod.DEBIT_CARD][creditcard['cpf']][creditcard['numero_cartao']] = creditcard

        response = GenericResponse(id, status=Status.SUCCESS.name, message="The card was successfully registered")
        return jsonify(GenericResponseSchema().dump(response)), HTTPStatus.OK, {"Content-Type": "application/json"}
    
    except ValidationError as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.BAD_REQUEST

    except BadRequestException as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.BAD_REQUEST
    
    except Exception as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.INTERNAL_SERVER_ERROR

@app.route('/validate', methods=['POST'])
def validate_card():
    # Generate a random ID for the response
    id = str(uuid.uuid4())

    try:
        request_data = request.get_json()
        validate_request = ValidateRequestSchema().load(request_data)

        if not NewCardValidator(validate_request).validate():
            raise BadRequestException(message="The given card is invalid")
        
        response = GenericResponse(id, status=Status.SUCCESS.name, message="The card is valid")
        return jsonify(GenericResponseSchema().dump(response)), HTTPStatus.OK, {"Content-Type": "application/json"}
    
    except ValidationError as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.BAD_REQUEST
    
    except BadRequestException as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.BAD_REQUEST
    
    except Exception as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.INTERNAL_SERVER_ERROR
    

@app.route('/buy', methods=['POST'])
def buy_with_card():
    # Generate a random ID for the response
    id = str(uuid.uuid4())

    try:
        request_data = request.get_json()
        buy_request = BuyRequestSchema().load(request_data)
        '''
        if not buy_request['cpf'] in registered_cards[buy_request['tipo_compra']]:
            raise NotFoundException(message="The given user is not registered in the system")
        
        if not registered_cards[buy_request['tipo_compra']][buy_request['cpf']]:
            raise UserNotHaveCardsException()

        if buy_request['numero_cartao'] in registered_cards[buy_request['tipo_compra']][buy_request['cpf']]:
            if(registered_cards[buy_request['tipo_compra']][buy_request['cpf']][buy_request['numero_cartao']]['cvv'] != buy_request['cvv']):
                raise InvalidValidationCodeException()
            if(buy_request['tipo_compra'] is PaymentMethod.DEBIT_CARD):
                if(buy_request['valor_compra'] > registered_cards[buy_request['tipo_compra']][buy_request['cpf']][buy_request['numero_cartao']]['saldo']):
                    raise InsufficientFundsException()
                else:
                    registered_cards[buy_request['tipo_compra']][buy_request['cpf']][buy_request['numero_cartao']]['saldo'] -= buy_request['valor_compra']
            else:
                if(buy_request['valor_compra'] > registered_cards[buy_request['tipo_compra']][buy_request['cpf']][buy_request['numero_cartao']]['credito']):
                    raise InsufficientFundsException()
                else:
                    registered_cards[buy_request['tipo_compra']][buy_request['cpf']][buy_request['numero_cartao']]['credito'] -= buy_request['valor_compra']
        else:
            raise CardNotExistsException()
        '''
        
        response = GenericResponse(id, status=Status.SUCCESS.name, message="The transaction was successfully processed")
        return jsonify(GenericResponseSchema().dump(response)), HTTPStatus.OK, {"Content-Type": "application/json"}
    
    except ValidationError as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.BAD_REQUEST
    
    except PaymentException as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.PAYMENT_REQUIRED

    except NotFoundException as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.NOT_FOUND

    except BadRequestException as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.BAD_REQUEST
    
    except Exception as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.INTERNAL_SERVER_ERROR
    
@app.route('/get/<cpf>', methods=['GET'])
def get_cards(cpf):
    # Generate a random ID for the response
    id = str(uuid.uuid4())

    try:
        if not cpf in registered_cards[PaymentMethod.CREDIT_CARD] and not cpf in registered_cards[PaymentMethod.DEBIT_CARD]:
            raise NotFoundException(message="The user is not registered in the system")
        
        credit_cards = dict()
        debit_cards = dict()
        if cpf in registered_cards[PaymentMethod.CREDIT_CARD]:
            for i, card in enumerate(registered_cards[PaymentMethod.CREDIT_CARD][cpf]):
                credit_cards[str(i)] = CardFilter(registered_cards[PaymentMethod.CREDIT_CARD][cpf][card]).filter()
        
        if cpf in registered_cards[PaymentMethod.DEBIT_CARD]:
            for i, card in enumerate(registered_cards[PaymentMethod.DEBIT_CARD][cpf]):
                debit_cards[str(i)] = CardFilter(registered_cards[PaymentMethod.DEBIT_CARD][cpf][card]).filter()

        response = GetCardsResponse(id, status=Status.SUCCESS.name, credit_cards=credit_cards, debit_cards=debit_cards)
        return jsonify(GetCardsResponseSchema().dump(response)), HTTPStatus.OK, {"Content-Type": "application/json"}
    
    except ValidationError as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.BAD_REQUEST

    except Exception as e:
        return jsonify(GenericResponseSchema().dump(GenericResponse(id=id, status=Status.ERROR.name, error=str(e)))), HTTPStatus.INTERNAL_SERVER_ERROR