package me.amitghosh.walmart.model

class Product(
    val logoName: String,
    val imageName: String,
    val productName: String,
    val productModel: String,
    val productDescription: String,
    val cost: Double
) {
    override fun toString(): String {
        return "$productModel : $$cost"
    }
}