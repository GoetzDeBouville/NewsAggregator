package com.example.core.data.network

import com.example.core.data.network.api.NewsApi
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class NewsApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: NewsApi

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
            .build()
            .create(NewsApi::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetchRss returns parsed RSS feed`() = runTest {
        val mockXml = """
            <rss version="2.0" xmlns:dc="http://purl.org/dc/elements/1.1/">
              <channel>
                <title>The Guardian</title>
                <link>https://www.theguardian.com</link>
                <description>Latest news</description>
                <item>
                  <title>Sample News</title>
                  <link>https://www.theguardian.com/sample</link>
                  <description>This is a sample description.</description>
                  <pubDate>Mon, 20 May 2024 12:00:00 GMT</pubDate>
                  <guid>sample-guid</guid>
                </item>
              </channel>
            </rss>
        """.trimIndent()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockXml)
                .addHeader("Content-Type", "application/xml")
        )


        val response = api.fetchRss()

        assertNotNull(response.channel)
        assertEquals("The Guardian", response.channel.title)
        assertEquals(1, response.channel.items.size)
        assertEquals("Sample News", response.channel.items[0].title)
        assertEquals("https://www.theguardian.com/sample", response.channel.items[0].link)
    }
}
