package com.example.proyecto_iib_alquilerautos

import com.example.proyecto_iib_alquilerautos.api.types.APIResponse
import java.io.IOException

interface APICallback {
    fun onSuccess(response: APIResponse)
    fun onError(error: IOException)
}
