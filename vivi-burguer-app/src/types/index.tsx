export type PaymentCard = {
  name: string;
  lastNumbers: string;
  fullNumber: string;
  issuer: string;
  type: string;
  cvv: string;
  expirationDate: string;
};

type CartaoTipo = "DEBITO" | "CREDITO";

interface Cartao {
  id: number;
  numero: string;
  cvv: string;
  titular: string;
  dataValidade: string; // Assuming this is a string, change the type if it's a Date
  bandeira: string;
  tipoCartao: CartaoTipo;
  cpf: string;
}

interface CarrinhoItem {
  nome: string;
  preco: number;
  quantidade: number;
}

interface Carrinho {
  itens: CarrinhoItem[];
}

interface OrderRequest {
  cartao: Cartao;
  carrinho: Carrinho;
}
