package com.fatec.solidariza.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fatec.solidariza.data.model.Registro

@Entity(tableName = "registro")
data class RegistroEntity(
    @PrimaryKey
    val id: Int = 0,
    val distribuidorId: Int,
    val quantidade: Int,
    val peso: Double,
    val preco: Double,
)

fun List<Registro>.toRegistroEntity(distribuidorId: Int): List<RegistroEntity> {
    return map {
        RegistroEntity(
            id = it.id,
            distribuidorId = distribuidorId,
            quantidade = it.quantidade,
            peso = it.peso,
            preco = it.preco,
        )
    }
}
