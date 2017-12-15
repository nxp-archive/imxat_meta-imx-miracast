FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://0001-ugly-use-new-gst_element_class_add_static_pad_templa.patch \
                   file://0002-dvdlpcmdec-rewrite-to-use-GstAudioDecoder.patch \
                   file://0003-dvdlpcmdec-add-support-for-another-format.patch \
                   file://0004-dvdlpcmdec-link-libgstbase-1.0.patch \
                   file://0006-dvdlpcmdec-add-support-for-yet-another-format.patch \
                   file://0007-dvdlpcmdec-crop-buffers-with-invalid-size.patch \
"

