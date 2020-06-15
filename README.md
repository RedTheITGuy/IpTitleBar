# IpTitleBar
A simple minecraft plugin to display the server ip on a boss bar, and to allow players to copy the ip easily.

## Commands
- /ip
  - Writes a message to the sender with the IP
  - If the sender is a player, they can click the message to copy the ip
- /changeip <config/ip>
  - Changes the ip to the string (or back to the default if config is used)
  - IP will reset on reload/restart
  
## Permissions
- IpTitleBar.ip
  - Allows for the use of the /ip command
  - Defaults to everyone
- IpTitleBar.changeip
  - Allows for the changing of the ip (through /changeip)
  - Defaults to OPs
