package br.com.gustavoalmeidacarvalho.solidariza.data.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import br.com.gustavoalmeidacarvalho.solidariza.data.model.Distribuidor
import br.com.gustavoalmeidacarvalho.solidariza.data.model.Registro

@Entity(tableName = "distribuidor")
data class DistribuidorEntity(
    @PrimaryKey
    val id: Int = 0,
    val nome: String,
    val produto: String,
    val regiao: String,
    val endereco: String,
)

data class DistribuidorWithRegistros(
    @Embedded val distribuidorEntity: DistribuidorEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "distribuidorId",
    )
    val registrosEntity: List<RegistroEntity>,
)

fun DistribuidorWithRegistros.toDistribuidor() = Distribuidor(
    id = distribuidorEntity.id,
    nome = distribuidorEntity.nome,
    produto = distribuidorEntity.produto,
    regiao = distribuidorEntity.regiao,
    endereco = distribuidorEntity.endereco,
    registros = registrosEntity.toRegistro(),
)

private fun List<RegistroEntity>.toRegistro(): List<Registro> {
    return map {
        Registro(
            id = it.id,
            quantidade = it.quantidade,
            peso = it.peso,
            preco = it.preco,
        )
    }
}
