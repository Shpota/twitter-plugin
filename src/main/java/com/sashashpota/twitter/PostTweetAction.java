package com.sashashpota.twitter;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;

import static com.intellij.ide.BrowserUtil.browse;
import static com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR;
import static com.intellij.openapi.util.IconLoader.getIcon;
import static java.net.URLEncoder.encode;
import static org.apache.commons.lang.StringUtils.isBlank;

public class PostTweetAction extends AnAction {

    private static final String TWITTER_INTENT_URL = "https://twitter.com/intent/tweet?text=";

    public PostTweetAction() {
        super(getIcon("icon/menuIcon.png"));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        String selectedText = getSelectedText(event);
        try {
            browse(TWITTER_INTENT_URL + encode(selectedText, "UTF-8"));
        } catch (UnsupportedEncodingException ignored) { }
    }

    @Override
    public void update(@NotNull AnActionEvent event) {
        boolean selectedTextIsBlank = isBlank(getSelectedText(event));
        Presentation presentation = event.getPresentation();
        presentation.setVisible(!selectedTextIsBlank);
    }

    private String getSelectedText(AnActionEvent event) {
        Editor editor = event.getData(EDITOR);
        if (editor != null) {
            CaretModel caretModel = editor.getCaretModel();
            Caret currentCaret = caretModel.getCurrentCaret();
            return currentCaret.getSelectedText();
        }
        return null;
    }
}
