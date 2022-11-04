package com.example.githubexplorer

import com.example.githubexplorer.data.remote.dto.RepoDto
import com.example.githubexplorer.data.remote.dto.toRepo
import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {
    @Test
    fun dataTransferObjectConversion() {
        val apiResponse = RepoDto(999, "Test", "www.hamsterdance.com", "Hamsters Dancing", 900)
        val repo = apiResponse.toRepo()
        assertEquals(repo.id, 999)
        assertEquals(repo.name, "Test")
        assertEquals(repo.url, "www.hamsterdance.com")
        assertEquals(repo.description, "Hamsters Dancing")
        assertEquals(repo.stars, 900)
    }

    @Test
    fun dataTransferObjectConversionNullDescription() {
        val apiResponse = RepoDto(999, "Test", "www.hamsterdance.com", null, 900)
        val repo = apiResponse.toRepo()
        assertEquals(repo.id, 999)
        assertEquals(repo.name, "Test")
        assertEquals(repo.url, "www.hamsterdance.com")
        assertEquals(repo.description, "No Description.")
        assertEquals(repo.stars, 900)
    }

    @Test
    fun repoContainsWellFormedUrl() {
        val apiResponse = RepoDto(999, "Test", "https://hamsterdance.com", null, 900)
        val repo = apiResponse.toRepo()
        assert(repo.url.contains(".com"))
    }
}