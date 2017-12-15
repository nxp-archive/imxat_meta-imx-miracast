FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://0006-MIRACAST-Setting-the-upstream-latency-to-a-GstClockT.patch \
                   file://0007-MIRACAST-gstbasesink-add-latency-control-in-the-sink.patch \
"
