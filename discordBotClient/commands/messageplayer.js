const { SlashCommandBuilder } = require('@discordjs/builders');
const axios = require('axios');

module.exports = {
    data: new SlashCommandBuilder()
        .setName('message')
        .setDescription('message specific player on server')
        .addStringOption(option => option.setName('target').setDescription('target to message'))
        .addStringOption(option => option.setName('message').setDescription('message to send'), true),
    async execute(interaction) {
        const target = interaction.options.get('target').value;
        const message = interaction.options.get('message').value;
         axios.get('http://127.0.0.1:8001/kill', {
            params: {
                target: target,
                message: message
            }
         }
        ).then(function (response) {
            interaction.reply(response.data);
        }).catch(function (error) {
            console.log(error);
            interaction.reply("Something went wrong");
          })
        return
    },
};