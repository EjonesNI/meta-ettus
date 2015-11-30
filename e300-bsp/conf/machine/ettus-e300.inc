#@TYPE: Machine
#@Name: Ettus Research E3XX.
#@DESCRIPTION: Machine configuration for the Ettus Research E3XX Software Radio.

MACHINEOVERRIDES = "ettus-e300:${MACHINE}"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-xlnx"
PREFERRED_VERSION_linux-xlnx ?= "3.14%"

MACHINE_FEATURES = "vfat ethernet alsa usbhost bluetooth ext2 wifi"

SERIAL_CONSOLE = "115200 ttyPS0"

include conf/machine/include/tune-zynq.inc
require conf/machine/include/machine-xilinx-default.inc
require conf/machine/include/machine-xilinx-board.inc

PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-xlnx"
PREFERRED_PROVIDER_virtual/boot-bin ?= "u-boot-spl-xlnx"

UBOOT_BINARY_ettus-e300 = "u-boot-dtb.${UBOOT_SUFFIX}"

UBOOT_MACHINE = "zynq_zc70x_config"

MACHINE_DEVICETREE := "e300-devicetree.dts"

MACHINE_EXTRA_RRECOMMENDS = "kernel-modules acpid rtimulib rtimulib-conf \
                             e3xx-udev-rules \
                             gpsd gps-config gpsd-gpsctl gps-utils \
                            "
 
UBOOT_ENV = "uEnv"
IMAGE_BOOT_FILES += "boot.bin fpga.bin uEnv.txt e300-devicetree.dtb"

EXTRA_IMAGEDEPENDS += "virtual/boot-bin"