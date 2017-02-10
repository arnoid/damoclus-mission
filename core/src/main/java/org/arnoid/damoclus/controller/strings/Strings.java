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
}
