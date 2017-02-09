package org.arnoid.damoclus.controller.strings;

public interface Strings {
    public static interface LoadingWindow {
        public static String title = "loading_window_title";
    }

    public static interface MainMenuWindow {
        public static String title = "main_menu_window_title";
        public static String btn_quit = "main_menu_btn_quit";
        public static String btn_new_game = "main_menu_btn_new_game";
        public static String btn_options = "main_menu_btn_options";
    }

    public static interface OptionsMenuWindow {
        public static String title = "main_menu_window_title";
        public static String btn_audio = "options_menu_btn_audio";
        public static String btn_video = "options_menu_btn_video";
        public static String btn_controllers = "options_menu_btn_controllers";
        public static String btn_back = "options_menu_btn_back";
    }
}
