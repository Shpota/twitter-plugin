package com.sashashpota.twitter

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR
import com.intellij.openapi.actionSystem.Presentation
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.CaretModel
import com.intellij.openapi.editor.Editor
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock

class PostTweetActionTest {
    @Test
    fun `should update visibility`() {
        val action = PostTweetAction()
        val event = mock(AnActionEvent::class.java)
        val editor = mock(Editor::class.java)
        given(event.getData(EDITOR)).willReturn(editor)
        val caretModel = mock(CaretModel::class.java)
        given(editor.caretModel).willReturn(caretModel)
        val currentCaret = mock(Caret::class.java)
        given(caretModel.currentCaret).willReturn(currentCaret)
        given(currentCaret.selectedText).willReturn("selected text")
        val presentation = Presentation()
        given(event.presentation).willReturn(presentation)

        action.update(event)

        assertEquals(presentation.isVisible, true)
    }

    @Test
    fun `should update visibility given blank selectedText`() {
        val action = PostTweetAction()
        val event = mock(AnActionEvent::class.java)
        val editor = mock(Editor::class.java)
        given(event.getData(EDITOR)).willReturn(editor)
        val caretModel = mock(CaretModel::class.java)
        given(editor.caretModel).willReturn(caretModel)
        val currentCaret = mock(Caret::class.java)
        given(caretModel.currentCaret).willReturn(currentCaret)
        given(currentCaret.selectedText).willReturn(" ")
        val presentation = Presentation()
        given(event.presentation).willReturn(presentation)

        action.update(event)

        assertEquals(presentation.isVisible, false)
    }

    @Test
    fun `should update visibility given null editor`() {
        val action = PostTweetAction()
        val event = mock(AnActionEvent::class.java)
        val presentation = Presentation()
        given(event.presentation).willReturn(presentation)

        action.update(event)

        assertEquals(presentation.isVisible, false)
    }
}