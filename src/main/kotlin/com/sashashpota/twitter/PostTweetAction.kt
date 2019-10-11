package com.sashashpota.twitter

import com.intellij.ide.BrowserUtil.browse
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR
import org.apache.commons.lang.StringUtils.isBlank
import org.jetbrains.annotations.NotNull
import java.io.UnsupportedEncodingException
import java.net.URLEncoder.encode

class PostTweetAction : AnAction() {

    override fun actionPerformed(@NotNull event: AnActionEvent) {
        val selectedText = selectedText(event)
        try {
            browse("https://twitter.com/intent/tweet?text="
                    + encode(selectedText!!, "UTF-8"))
        } catch (ignored: UnsupportedEncodingException) { }
    }

    override fun update(@NotNull event: AnActionEvent) {
        val selectedTextBlank = isBlank(selectedText(event))
        event.presentation.isVisible = !selectedTextBlank
    }

    private fun selectedText(event: AnActionEvent): String? {
        val editor = event.getData(EDITOR)
        if (editor != null) {
            return editor.caretModel.currentCaret.selectedText
        }
        return null
    }
}
