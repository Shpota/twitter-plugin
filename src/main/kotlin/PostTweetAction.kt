package com.sashashpota.twitter

import com.intellij.ide.BrowserUtil.browse
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR
import java.net.URLEncoder.encode

class PostTweetAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) =
            browse("https://twitter.com/intent/tweet?text="
                    + encode(selectedText(event), "UTF-8"))

    override fun update(event: AnActionEvent) {
        event.presentation.isVisible = selectedText(event).isNotBlank()
    }

    private fun selectedText(event: AnActionEvent) =
            event.getData(EDITOR)?.caretModel?.currentCaret?.selectedText ?: ""
}
