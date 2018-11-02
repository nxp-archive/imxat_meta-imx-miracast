FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://miracast.cfg \
"

addtask patch_defconfig after do_patch before do_copy_defconfig
do_patch_defconfig() {
          cat ${WORKDIR}/miracast.cfg >> ${S}/arch/arm/configs/imx_v7_defconfig
}
