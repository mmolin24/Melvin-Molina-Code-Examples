#[derive(Debug)]
#[derive(PartialEq)]
pub enum Command
{
    Power(bool,i32),    // [Increase/Decrease] power by [number].
    Missiles(bool,i32), // [Increase/Decrease] missiles by [number].
    Shield(bool),       // Turn [On/Off] the shield.
    Try,                // Try calling pepper.
    Invalid             // [anything else]
}


/**
    Adds functionality to Command enums
    Commands can be converted to strings with the as_str method
    
    Command     |     String format
    ---------------------------------------------------------
    Power       |  /Power (increased|decreased) by [0-9]+%/
    Missiles    |  /Missiles (increased|decreased) by [0-9]+/
    Shield      |  /Shield turned (on|off)/
    Try         |  /Call attempt failed/
    Invalid     |  /Not a command/
**/
impl Command {
    pub fn as_str (&self) -> String {
        match self{
            Command::Power(ref a, ref b) => if *a {
                // if the command is equal to power increase
                let mut ret_str = String::from("Power increased by ");

                ret_str.push_str(b.to_string().as_mut_str());

                ret_str.push_str("%");

                return ret_str;
            }else{
                // if command is power, but decrease
                let mut ret_str = String::from("Power decreased by ");

                ret_str.push_str(b.to_string().as_mut_str());

                ret_str.push_str("%");

                return ret_str;
            },
            Command::Missiles(a,b) => if *a{
                // if command is missile increase
                let mut ret_str = String::from("Missiles increased by ");

                ret_str.push_str(b.to_string().as_mut_str());

                return ret_str;
            }else{
                // if command is missiles decrease by
                let mut ret_str = String::from("Missiles decreased by ");

                ret_str.push_str(b.to_string().as_mut_str());

                return ret_str;
            },
            Command::Shield(a) => if *a{
                // if command is shield on 
                let mut ret_str = String::from("Shield turned on");

                return ret_str;
            }else{
                // if command is shield off
                let mut ret_str = String::from("Shield turned off");

                return ret_str;
            },
            Command::Try => {
                // if call attempt fals
                let mut ret_str = String::from("Call attempt failed");

                return ret_str;
            },
            Command::Invalid => {
                // if command is not
                let mut ret_str = String::from("Not a command");

                return ret_str;
            }

        }
    }
}

/**
    Complete this method that converts a string to a command 
    We list the format of the input strings below
    Command     |     String format
    ---------------------------------------------
    Power       |  /power (inc|dec) [0-9]+/
    Missiles    |  /(fire|add) [0-9]+ missiles/
    Shield      |  /shield (on|off)/
    Try         |  /try calling Miss Potts/
    Invalid     |  Anything else
**/
pub fn to_command(s: &str) -> Command {
    let vect: Vec<&str> = s.split(' ').collect();

    if vect[0] == "power" && vect.len() == 3{
        if vect[1] == "inc" {
            // matches if the power is to increase
            return Command::Power(true, vect[2].parse::<i32>().unwrap());

        }else if vect[1] == "dec"{
            // matches if the power is going to decrease
            return Command::Power(false, vect[2].parse::<i32>().unwrap());

        }else{
            // if not matched then the command will be invalid
            return Command::Invalid;
        }
    }else if (vect[0] == "fire" || vect[0] == "add") && vect.len() == 3{
        // reaches here when the command has something to do with missiles
        if vect[0] == "fire" && vect[2] == "missiles"{

            // reaches if command is to fire missiles and sends through with digit amt to fire
            return Command::Missiles(false,vect[1].parse::<i32>().unwrap());

        }else if vect[0] == "add" && vect[2] == "missiles"{

            // reaches if command is to add missiles and captures the amount
            return Command::Missiles(true,vect[1].parse::<i32>().unwrap());

        }else{

            // only reaches here if the command is invalid 
            return Command::Invalid;

        }
    }else if vect[0] == "shield" && vect.len() == 2{
        // reaches when the first word of the command is shield
        if vect[1] == "on"{

            // will get here if command is to turn shield on
            return Command::Shield(true);

        }else if vect[1] == "off"{

            // will get here if the command is to turn shield off
            return Command::Shield(false);

        }else{

            // anything other than off or on and it's invalid
            return Command::Invalid;

        }
    }else if s == "try calling Miss Potts"{

        // lastly the command is to try calling ms pots
        return Command::Try;

    }else{

        // reaches here if not captured by any other of the matches
        return Command::Invalid;

    }
}