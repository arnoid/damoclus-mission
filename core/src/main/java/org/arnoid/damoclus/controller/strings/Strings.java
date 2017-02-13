package org.arnoid.damoclus.controller.strings;

public interface Strings {
    interface LoadingWindow {
        String title = "loading_window_title";
    }

    interface MainMenuWindow {
        String title = "main_menu_window_title";
        String btn_quit = "main_menu_btn_quit";
        String btn_new_game = "main_menu_btn_new_game";
        String btn_options = "main_menu_btn_options";
    }

    interface OptionsMenuWindow {
        String title = "main_menu_window_title";
        String btn_audio = "options_menu_btn_audio";
        String btn_language = "options_menu_btn_language";
        String btn_video = "options_menu_btn_video";
        String btn_controllers = "options_menu_btn_controllers";
        String btn_back = "options_menu_btn_back";
    }

    interface LanguageMenuWindow {
        String title = "language_menu_window_title";
        String en = "language_menu_en";
        String ru = "language_menu_ru";
        String btn_back = "language_menu_btn_back";
    }

    interface VideoMenuWindow {
        String title = "video_menu_window_title";
        String lbl_resolution = "video_menu_label_resolution";
        String select_resolution = "video_menu_selectbox_resolution";
        String chk_fullscreen = "video_menu_chk_fullscreen";
        String lbl_fullscreen = "video_menu_label_fullscreen";
        String btn_back = "video_menu_btn_back";
        String btn_apply = "video_menu_btn_apply";
        String btn_cancel = "video_menu_btn_cancel";

    }

    interface AudioMenuWindow {
        String title = "audio_menu_window_title";
        String btn_back = "audio_menu_btn_back";
    }
}
