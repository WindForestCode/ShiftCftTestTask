package com.myschool.shiftcft.domain.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.SerializationException

@Serializable(with = PostcodeSerializer::class)
data class Postcode(val value: String)

object PostcodeSerializer : KSerializer<Postcode> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Postcode", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Postcode) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder): Postcode {
        val jsonDecoder =
            decoder as? JsonDecoder ?: throw SerializationException("Expected JsonDecoder")
        val postcodeString = when (val jsonElement: JsonElement = jsonDecoder.decodeJsonElement()) {
            is JsonPrimitive -> {
                jsonElement.content
            }

            else -> throw SerializationException("Unexpected type for postcode: $jsonElement")
        }

        return Postcode(postcodeString)
    }
}




