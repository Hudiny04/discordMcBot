const { SlashCommandBuilder } = require('@discordjs/builders');
const axios = require('axios');
require('dotenv').config();

module.exports = {
    data: new SlashCommandBuilder()
        .setName('message')
        .setDescription('message specific player on server')
        .addStringOption(option => option.setName('target').setDescription('target to message'))
        .addStringOption(option => option.setName('message').setDescription('message to send')),
    async execute(interaction) {
        const author =interaction.member.user.username
        const target = interaction.options.get('target').value;
        const message = interaction.options.get('message').value;
         axios.get(`${process.env.API}:${process.env.PORT}/message`, {
            params: {
                target: target,
                message: message,
                author: author
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