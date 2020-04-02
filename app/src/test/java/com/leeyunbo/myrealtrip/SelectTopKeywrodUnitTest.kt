package com.leeyunbo.myrealtrip

import com.leeyunbo.myrealtrip.util.SelectTopKeyword
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Before
import org.junit.Test
/*
 * SelectTopKeyword class의 유닛 테스트를 위한 테스트 코드
 */
class SelectTopKeywrodUnitTest {
    private lateinit var mInstance : SelectTopKeyword

    @Before
    fun setUp() {
        mInstance = SelectTopKeyword
    }

    @Test
    fun instanceIsNotNull() {
        assertNotNull(mInstance)
    }

    @Test
    fun getTopKeywrod_isCorrect() {
        Assert.assertEquals(mInstance.getTopKeywords("one two three"),mInstance.getTopKeywords("one two three"))
        Assert.assertEquals(mInstance.getTopKeywords("abc every one")?.get(0), mInstance.getTopKeywords("abc zzz zss")?.get(0))
        assertNotNull(mInstance.getTopKeywords(""))
    }

    @Test
    fun getTopKeywordCount() {
        Assert.assertEquals(mInstance.getTopKeywords("")?.size, 0)
        Assert.assertEquals(mInstance.getTopKeywords("one")?.size,1)
        Assert.assertEquals(mInstance.getTopKeywords("one two")?.size,2)
        Assert.assertEquals(mInstance.getTopKeywords("one two three")?.size,3)
        Assert.assertEquals(mInstance.getTopKeywords("one two three four five")?.size,3)
    }


}