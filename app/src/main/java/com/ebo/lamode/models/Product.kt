package com.ebo.lamode.models

class Product(val id: String, val name: String, val text: String, val imageUrl: String, val detailImageUrl: String, val price: Double, val discountPrice: Double, val rating: Double) {
    constructor() : this("", "", "", "", "", 0.0, 0.0, 0.0)
}