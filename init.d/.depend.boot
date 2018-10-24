TARGETS = console-setup mountkernfs.sh resolvconf ufw screen-cleanup apparmor hostname.sh x11-common plymouth-log udev keyboard-setup cryptdisks cryptdisks-early networking checkroot.sh lvm2 hwclock.sh urandom iscsid checkfs.sh mountdevsubfs.sh open-iscsi procps bootmisc.sh mountall.sh checkroot-bootclean.sh mountnfs-bootclean.sh mountnfs.sh mountall-bootclean.sh kmod
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
networking: mountkernfs.sh urandom resolvconf procps
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
hwclock.sh: mountdevsubfs.sh
urandom: hwclock.sh
iscsid: networking
checkfs.sh: cryptdisks checkroot.sh lvm2
mountdevsubfs.sh: mountkernfs.sh udev
open-iscsi: networking iscsid
procps: mountkernfs.sh udev
bootmisc.sh: udev mountnfs-bootclean.sh checkroot-bootclean.sh mountall-bootclean.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
checkroot-bootclean.sh: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
mountall-bootclean.sh: mountall.sh
kmod: checkroot.sh
