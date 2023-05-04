const { Client, Intents, Collection } = require('discord.js');
const fs = require('node:fs');
require('dotenv').config();
const { CommandDeploy } = require('./deploy.js');
const client = new Client({ intents: [Intents.FLAGS.GUILDS, Intents.FLAGS.GUILD_MESSAGES] });

client.commands = new Collection();
const commandFiles = fs.readdirSync('./commands').filter(file => file.endsWith('.js'));

for (const file of commandFiles) {
    const command = require(`./commands/${file}`);
    client.commands.set(command.data.name, command);
}

client.once('ready', async () => {
    console.log('Ready!');
    guild = client.guilds.cache.forEach(guild => {
        CommandDeploy(guild.id);
    });
    
});

client.on('interactionCreate', async interaction => {
    if (!interaction.isCommand()) return;
    if (interaction.user.bot) return;
    const command = client.commands.get(interaction.commandName);
    if (!command) return;
    try {
        await command.execute(interaction);
        console.log("Command succesfull")
    } catch (error) {
        console.error(error);
        await interaction.reply({ content: 'Wrong command', ephemeral: true });
    }
});

client.login(process.env.TOKEN);
