FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://miracast.cfg \
            file://0001-Murata-patch-for-imx-linux-4.1.15_2.0.0_ga.patch \
            file://0002-SN8000-fix-on-imx6ul.patch \
            file://0003-Add-OOB-IRQ-support-on-imx6ul.patch \
"

addtask patch_defconfig after do_patch before do_copy_defconfig
do_patch_defconfig() {
          cat ../*.cfg >> ${S}/arch/arm/configs/imx_v7_defconfig
}
