Crashing the Android Webview
============================

This repo contains a small example that crashes the android webview when using a file url and createObjectURL.

The crash will look like

    [ERROR:render_process_host_impl.cc(5148)] Terminating render process for bad Mojo message: Received bad user message: Non committable URL passed to BlobURLStore::Register
    [ERROR:bad_message.cc(26)] Terminating renderer for bad IPC message, reason 123
    [ERROR:aw_browser_terminator.cc(123)] Renderer process (14814) crash detected (code -1).
    [ERROR:aw_browser_terminator.cc(89)] Render process (14814) kill (OOM or update) wasn't handed by all associated webviews, killing application.

