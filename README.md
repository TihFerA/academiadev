# academiadev
projeto do desafio academiaDev
Sobre seu desafio:

O contexto do desafio é o de uma loja que vende produtos e contrata serviços. O objetivo desse desafio é desenvolver uma aplicação para calcular o fluxo de caixa diário dado um conjunto de dados previamente fornecido.

Dados:

catalog.csv Aqui você encontrará os produtos vendidos pela loja e seus preços indexados pelo id dos mesmos.

sales.jsonl Todas as vendas da loja estão neste arquivo. O horário da compra é definido pela coluna timestamp e o produto por product_id. Na coluna n_payments você encontrará em quantas vezes será feito o pagamento desta compra e se foi débito ou crédito na coluna payment_method.

purchases.jsonl As compras feitas pela loja estão aqui. As colunas são similares aos dados de vendas.

Informações importantes:

É preciso considerar que a data de fechamento do cartão de crédito é no dia 5 de cada mês. Porém, a parcela em si cai apenas no dia 10. Logo, se uma compra ou pagamento foi feito dia 2017-09-05 em diante, a primeira parcela cairá no dia 2017-10-10. Já se tivesse sido no dia 2017-09-04, cairia em 2017-09-10.
Se o método de pagamento for débito, cairá no mesmo instante descrito na coluna timestamp.
O conjunto de dados é gerado dinamicamente para cada pessoa que se inscreve no desafio. Logo, não se assuste se a de seu colega estiver diferente.

Note que o valor diário deve ser arredondado para o inteiro mais próximo.
