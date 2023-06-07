{
  description = "My Android project";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    devshell.url = "github:numtide/devshell";
    flake-utils.url = "github:numtide/flake-utils";
    android.url = "github:tadfisher/android-nixpkgs/beta";
  };

  outputs = { self, nixpkgs, devshell, flake-utils, android }:
    {
      overlay = final: prev: {
        inherit (self.packages.${final.system}) android-sdk android-studio;
      };
    }
    //
    flake-utils.lib.eachSystem [ "x86_64-linux" ] (system:
      let
        pkgs = import nixpkgs {
          inherit system;
          config.allowUnfree = true;
          overlays = [
            devshell.overlays.default
            self.overlay
          ];
        };
      in
      {
        packages = {
          android-sdk = android.sdk.${system} (sdkPkgs: with sdkPkgs; [
            # Useful packages for building and testing.
            build-tools-33-0-2
            cmdline-tools-latest
            emulator
            platform-tools
            platforms-android-33
            sources-android-33
            system-images-android-29-google-apis-x86
	    add-ons-addon-google-apis-google-9
          ]);
          android-studio = pkgs.androidStudioPackages.stable;
        };

        devShell = import ./devshell.nix { inherit pkgs; };
      }
    );
}
