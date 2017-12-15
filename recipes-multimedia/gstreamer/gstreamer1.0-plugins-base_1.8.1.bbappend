FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Miracast specific
SRC_URI_append = " file://0013-Let-the-faad-parse-and-never-fail.patch \
                   file://0014-MIRACAST-gstaudiobasesink-Adding-the-upstream-latenc.patch \
"
