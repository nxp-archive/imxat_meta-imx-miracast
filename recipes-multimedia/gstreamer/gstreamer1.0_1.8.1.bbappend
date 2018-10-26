FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://0001-MIRACAST-gstbasesink-add-latency-control-in-the-sink.patch \
"
