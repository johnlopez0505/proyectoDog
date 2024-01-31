package com.john.proyectodog.data.models

data class Dog(
    val name: String,
    val image: String
)
{
    override fun toString(): String {
        return "Dog(name='$name', image='$image')"
    }

}