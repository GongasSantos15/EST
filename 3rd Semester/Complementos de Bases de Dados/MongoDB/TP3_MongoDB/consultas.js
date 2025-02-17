db.Funcionario.find({ nome_funcionario: { $eq: "André" } })
db.Funcionario.find({ contatos_telefonicos: { $gt: 919119112 } })
db.Funcionario.find({ data_nascimento: { $lt: "07-01-2000" } })
db.Funcionario.find({ id: { $ne: null } })
db.Funcionario.find({ $and: [{ contatos_telefonicos: { $gte: 919119112 } }, { nome_funcionario: "Beatriz" }] })

db.Funcionario.find({ $and: [{ nome_funcionario: "Carlos" }, { id: { $lt: 5 } }] })
db.Funcionario.find({ $or: [{ contatos_telefonicos: { $lte: 919119111 } }, { nome_funcionario: "Bernardo" }] })
db.Funcionario.find({ nome_funcionario: { $not: { $eq: "David" } } })
db.Funcionario.find({ contatos_telefonicos: { $ne: 919119113 } })
db.Funcionario.find({ $and: [{ contatos_telefonicos: { $ne: null } }, { $or: [{ nome_funcionario: "Gonçalo" }, { data_nascimento: { $lt: "02-01-2000" } }] } ] })

db.Funcionario.find({ contatos_telefonicos: { $size: 2 } })
db.Funcionario.find({ id: { $size: 4 } }).count()