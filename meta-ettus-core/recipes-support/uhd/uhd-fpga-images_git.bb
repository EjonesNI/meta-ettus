FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit uhd_images_downloader
require recipes-support/uhd/version.inc
require includes/maintainer-ettus.inc

DEPENDS += "dtc-native python3-native"

SRC_URI_append = " \
    file://LICENSE.md \
    file://fpga_bit_to_bin.py \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES += "${PN}-firmware"

ALLOW_EMPTY_${PN} = "1"
ALLOW_EMPTY_${PN}-firmware = "1"

LICENSE = "LGPLv3+"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE.md;md5=2d2b59b2fc606f13eb2631fb80325865"

UHD_IMAGES_INSTALL_PATH ?= "/usr/share/uhd/images"

# FPGA images to include in the /lib/firmare directory
# This variable needs to be appended with a machine specific overwrite
UHD_FPGA_IMAGES_IN_FIRMWARE ??= ""

do_compile_append() {
    # the files are provided by uhd-fpga-images
    for IMAGE in ${UHD_FPGA_IMAGES_IN_FIRMWARE}; do
        python3 ${WORKDIR}/fpga_bit_to_bin.py -f ${S}/$IMAGE.bit ${WORKDIR}/$IMAGE.bin
        dtc -@ -o ${WORKDIR}/$IMAGE.dtbo ${S}/$IMAGE.dts
    done
}

do_install_append() {
    for IMAGE in ${UHD_FPGA_IMAGES_IN_FIRMWARE}; do
        mkdir -p  ${D}/lib/firmware/
        install -m 0644 ${WORKDIR}/$IMAGE.bin ${D}/lib/firmware/
        install -m 0644 ${WORKDIR}/$IMAGE.dtbo ${D}/lib/firmware/
    done
}
