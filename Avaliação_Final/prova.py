import pymongo

# Conecta ao mongo
client = pymongo.MongoClient("mongodb://localhost:27017/")

# Acessa ao banco de dados
db = client["Gestao_de_Financas_Pessoais"]

# Cria uma coleção
contas_bancarias = db["Contas Bancárias"]

# Faz a inserção de informações
conta1 = {"agencia": "1234", "tipo_de_conta": "corrente", "saldo": 1000}
conta2 = {"agencia": "5678", "tipo_de_conta": "corrente", "saldo": 2000}
conta3 = {"agencia": "9876", "tipo_de_conta": "poupança", "saldo": 500}
conta4 = {"agencia": "4321", "tipo_de_conta": "corrente", "saldo": 3000}

contas_bancarias.insert_many([conta1, conta2, conta3, conta4])

# Atualização do saldo de uma conta pela chave "agencia"
contas_bancarias.update_one({"agencia": "1234"}, {"$set": {"saldo": 1500}})

# Seleção de contas por tipo "corrente"
contas_corrente = contas_bancarias.find({"tipo_de_conta": "corrente"})

print('As correntas correntes são:')
for conta in contas_corrente:
    print(conta)

# Agregação: Calcule a média do saldo das contas
pipeline = [
    {"$group": {"_id": None, "media_saldo": {"$avg": "$saldo"}}}
]
resultado_agregacao = list(contas_bancarias.aggregate(pipeline))

print()
valor_media_final = float(resultado_agregacao[0]["media_saldo"])
print(f"Média do saldo das contas: R$ {valor_media_final:.2f}" )
